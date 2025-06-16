package com.proxxy.store.domain.service;

import com.proxxy.store.domain.exception.InventoryOperationException;
import com.proxxy.store.domain.exception.NameInUseException;
import com.proxxy.store.domain.exception.ProductNotFoundException;
import com.proxxy.store.domain.model.Category;
import com.proxxy.store.domain.model.Product;
import com.proxxy.store.domain.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    private Product mouser;
    private Product computer;
    private Product computerX;
    private Product keyboard;

    private Category gamer;
    private Category electronic;

    private List<Long> categoryIds;
    private List<Category> categories;

    @BeforeEach
    void setUp() {

        gamer = new Category();
        gamer.setId(1L);
        gamer.setName("Gamer");
        gamer.setDescription("Acessórios para o public gamer.");

        electronic = new Category();
        electronic.setId(2L);
        electronic.setName("Eletrônico");
        electronic.setDescription("Ferramentas e hardware para sua casa.");

        categoryIds = new ArrayList<>(1);
        categories = new ArrayList<>(1);

        categories.add(electronic);
        categories.add(gamer);
        categoryIds.add(electronic.getId());
        categoryIds.add(gamer.getId());

        mouser = new Product();
        mouser.setId(UUID.randomUUID());
        mouser.setName("Mouser ZX");
        mouser.setDescription("Perfeito para gameplay longas e trabalho.");
        mouser.setImageLink("http://clound/images/mouser-zx.jpg");
        mouser.setCategories(new HashSet<>(categories));
        mouser.setAvailableQuantity(100);
        mouser.setValue(BigDecimal.valueOf(75.20));

        computer = new Product();
        computer.setId(UUID.randomUUID());
        computer.setName("Xeon x99");
        computer.setDescription("Perfeito para trabalho e games.");
        computer.setImageLink("http://clound/images/computer-xeon.jpg");
        computer.setCategories(new HashSet<>());
        computer.setAvailableQuantity(100);
        computer.setValue(BigDecimal.valueOf(4525.99));

        computerX = new Product();
        computerX.setId(UUID.randomUUID());
        computerX.setName("Xeon x99");
        computerX.setDescription("Perfeito para trabalho e games.");
        computerX.setImageLink("http://clound/images/computer-xeon-99.jpg");
        computerX.setCategories(new HashSet<>());
        computerX.setAvailableQuantity(100);
        computerX.setValue(BigDecimal.valueOf(4525.99));

        keyboard = new Product();
        keyboard.setName("Keyboard ZX");
        keyboard.setDescription("Perfeito para longos períodos de digitação e gameplay.");
        keyboard.setImageLink("http://clound/images/keyboard-zx.jpg");
        keyboard.setCategories(Set.of(gamer));
        keyboard.setAvailableQuantity(320);
        keyboard.setValue(BigDecimal.valueOf(246.99));
    }

    @Test
    @DisplayName("Deve retornar o produto quando ele existir")
    void shouldReturnProductWhenIdExists() {

        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));

        Product product = productService.findById(mouser.getId());
        Assertions.assertEquals(product, mouser);
    }

    @Test
    @DisplayName("Deve lançar ProductNotFoundException quando o ID não for encontrado")
    void shouldThrowProductNotFoundExceptionWhenIdNotFound() {

        UUID uuid = UUID.randomUUID();
        Mockito.when(productRepository.findById(uuid)).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById(uuid));
    }

    @Test
    @DisplayName("Deve salvar e retornar produto quando o nome não estiver em uso")
    void shouldSaveAndReturnProductWhenNameIsUnique() {

        Mockito.when(productRepository.findByName(keyboard.getName())).thenReturn(Optional.empty());
        Mockito.when(productRepository.save(keyboard)).thenReturn(keyboard);

        Product product = productService.addProduct(keyboard);
        Assertions.assertEquals(keyboard, product);
    }

    @Test
    @DisplayName("Deve lançar NameInUseException ao adicionar produto com nome duplicado")
    void shouldThrowNameInUseExceptionWhenAddingWithDuplicateName() {

        Mockito.when(productRepository.findByName(mouser.getName())).thenReturn(Optional.of(mouser));
        Assertions.assertThrows(NameInUseException.class, () -> productService.addProduct(mouser));
    }

    @Test
    @DisplayName("Deve anexar categorias ao produto")
    void shouldAttachCategoriesToProduct() {

        Mockito.when(categoryService.findAllById(categoryIds)).thenReturn(categories);
        Mockito.when(productRepository.findById(computer.getId())).thenReturn(Optional.of(computer));

        productService.attachCategory(computer.getId(), categoryIds);
        Assertions.assertTrue(computer.getCategories().contains(electronic));
    }

    @Test
    @DisplayName("Deve desanexar categoria do produto")
    void shouldDetachCategoryFromProduct() {

        Mockito.when(categoryService.findAllById(List.of(gamer.getId()))).thenReturn(List.of(gamer));
        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));

        productService.detachCategory(mouser.getId(), List.of(gamer.getId()));
        Assertions.assertFalse(mouser.getCategories().contains(gamer));
    }

    @Test
    @DisplayName("Deve atualiza o valor do produto")
    void shouldUpdateProductPrice() {

        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));
        BigDecimal newPrice = BigDecimal.valueOf(Math.random());

        productService.updatePrice(mouser.getId(), newPrice);
        Assertions.assertEquals(newPrice, mouser.getValue());
    }

    @Test
    @DisplayName("Deve lançar NameInUseException ao atualizar com nome duplicado")
    void shouldThrowNameInUseExceptionWhenUpdatingWithDuplicateName() {

        Mockito.when(productRepository.findByName(computerX.getName())).thenReturn(Optional.of(computer));
        Assertions.assertThrows(NameInUseException.class, () -> productService.updateProduct(computerX));
    }

    @Test
    @DisplayName("Deve atualizar e retornar produto quando o nome não estiver em uso")
    void shouldUpdateAndReturnProductWhenNameIsUnique(){

        Mockito.when(productRepository.save(computer)).thenReturn(computer);
        Mockito.when(productRepository.findByName(computer.getName())).thenReturn(Optional.empty());

        productService.updateProduct(computer);
        Mockito.verify(productRepository, Mockito.times(1)).save(computer);

    }

    @Test
    @DisplayName("Deve aumentar o estoque do produto pela quantidade informada")
    void shouldIncreaseStockByQuantity() {

        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));
        Integer currentStock = mouser.getAvailableQuantity();

        productService.increaseStock(mouser.getId(), 50);
        Assertions.assertEquals((currentStock + 50), mouser.getAvailableQuantity());
    }

    @Test
    @DisplayName("Deve diminuir o estoque do produto pela quantidade informada")
    void shouldDecreaseStockByQuantity() {

        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));
        Integer currentStock = mouser.getAvailableQuantity();

        productService.decreaseStock(mouser.getId(), 50);
        Assertions.assertEquals((currentStock - 50), mouser.getAvailableQuantity());
    }

    @Test
    @DisplayName("Deve lançar InventoryOperationException ao reduzir mais que o estoque disponível")
    void shouldThrowInventoryOperationExceptionWhenDecreaseExceedsStock() {

        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));
        Assertions.assertThrows(InventoryOperationException.class,
                () -> productService.decreaseStock(mouser.getId(), 1000));
    }

    @Test
    @DisplayName("Deve Deletar o produto quando ele existir")
    void shouldDeleteProductWhenIdExists(){

        Mockito.when(productRepository.findById(mouser.getId())).thenReturn(Optional.of(mouser));
        Assertions.assertDoesNotThrow(() -> productService.deleteProduct(mouser.getId()));
    }

    @Test
    @DisplayName("Deve retornar página vazia quando não houver produtos")
    void shouldReturnEmptyPageWhenNoProducts() {

        Pageable pageable = Pageable.ofSize(10);
        Page<Product> emptyPage = new PageImpl<>(List.of(), pageable, 0);

        Mockito.when(productRepository.findAll(pageable)).thenReturn(emptyPage);

        Page<Product> result = productService.findAll(pageable);
        Mockito.verify(productRepository).findAll(pageable);
        Assertions.assertTrue(result.isEmpty());
    }
}