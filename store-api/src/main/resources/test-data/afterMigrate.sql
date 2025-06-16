insert into tb_category(id, name, description) values(1, "Gamer", "Produtos e ferramentas para o publico gamer.");
insert into tb_category(id, name, description) values(2, "Eletrônico", "componentes e periféricos.");
insert into tb_category(id, name, description) values(3, "Eletro domestico", "Conforto e tecnologia para sua casa.");

insert into tb_product(id, name, description, value, available_quantity, image_link)
values(UUID_TO_BIN("17442774-a121-447d-a1b8-759febdb4053"), "Teclado Mecânico", "Perfeito para games online.", 110.00, 20, "http://server:8080/photos/1");

insert into tb_product(id, name, description, value, available_quantity, image_link)
values(UUID_TO_BIN("d79027f9-01f5-4448-bf1d-54eefa357c3f"), "TV LED 70", "TV LED 4K.", 2500.00, 500, "http://server:8080/photos/2");

insert into tb_product(id, name, description, value, available_quantity, image_link)
values(UUID_TO_BIN("35bdd007-ef2a-47c8-b00c-2b6b047a78d5"), "Gabinete ZX", "Perfeito para maquinas de alto desempenho.", 260.00, 320, "http://server:8080/photos/3");

insert into tb_product(id, name, description, value, available_quantity, image_link)
values(UUID_TO_BIN("cb3e7422-8736-4fe0-80c9-4cb970aebf29"), "Monitor 24", "Monitor perfeito para Trabalho e Games.", 750.00, 90, "http://server:8080/photos/4");

insert into tb_product(id, name, description, value, available_quantity, image_link)
values(UUID_TO_BIN("0945f559-706b-41e1-bd13-7b1b70cd79a6"), "SSD NVME 250GB", "+ desempenho para sua maquina", 520.00, 110, "http://server:8080/photos/5");

insert into tb_product(id, name, description, value, available_quantity, image_link)
values(UUID_TO_BIN("a80bf542-e995-4b55-bce6-2ec6e5171acc"), "Mouser xz", "Perfeito para Trabalho e Games.", 97.60, 200, "http://server:8080/photos/6");

insert into tb_product_category(category_id, product_id)
values(1, UUID_TO_BIN("17442774-a121-447d-a1b8-759febdb4053"));

insert into tb_product_category(category_id, product_id)
values(3, UUID_TO_BIN("d79027f9-01f5-4448-bf1d-54eefa357c3f"));

insert into tb_product_category(category_id, product_id)
values(1, UUID_TO_BIN("35bdd007-ef2a-47c8-b00c-2b6b047a78d5"));

insert into tb_product_category(category_id, product_id)
values(2, UUID_TO_BIN("cb3e7422-8736-4fe0-80c9-4cb970aebf29"));

insert into tb_product_category(category_id, product_id)
values(2, UUID_TO_BIN("0945f559-706b-41e1-bd13-7b1b70cd79a6"));

insert into tb_product_category(category_id, product_id)
values(1, UUID_TO_BIN("a80bf542-e995-4b55-bce6-2ec6e5171acc"));

insert into tb_product_category(category_id, product_id)
values(2, UUID_TO_BIN("a80bf542-e995-4b55-bce6-2ec6e5171acc"));

insert into tb_product_category(category_id, product_id)
values(1, UUID_TO_BIN("cb3e7422-8736-4fe0-80c9-4cb970aebf29"));

insert into tb_group(id, name, description)
values(1, "ADMIN", "Administrative permissions");

insert into tb_permission(id, name, description)
values(1, "EDIT_PRODUCT", "Allows to create and edit products");

insert into tb_permission(id, name, description)
values(2, "EDIT_CATEGORY", "Allows to create and edit categories");

insert into tb_user(id, name, email, password)
values(UUID_TO_BIN("11b899b4-669d-42db-9c0e-b4d2763f6702"), "Eduardo", "EduardoSantos@email.com", "$2a$12$s/mXA.l1AZ9F7IdWjpF55ePg/G8E8nyklZQ1rvezSNuEAAtcD51sa");

insert into tb_user(id, name, email, password)
values(UUID_TO_BIN("f8093e78-729c-42bc-8679-999ce449c45a"), "Maria", "MariaSilva@email.com", "$2a$12$s/mXA.l1AZ9F7IdWjpF55ePg/G8E8nyklZQ1rvezSNuEAAtcD51sa");

insert into tb_group_permission(group_id, permission_id)
values(1,1);

insert into tb_group_permission(group_id, permission_id)
values(1,2);

insert into tb_user_group(user_id, group_id)
values(UUID_TO_BIN("11b899b4-669d-42db-9c0e-b4d2763f6702"), 1);

INSERT INTO `oauth2_registered_client` (
    `id`,
    `client_id`,
    `client_id_issued_at`,
    `client_secret`,
    `client_secret_expires_at`,
    `client_name`,
    `client_authentication_methods`,
    `authorization_grant_types`,
    `redirect_uris`,
    `post_logout_redirect_uris`,
    `scopes`,
    `client_settings`,
    `token_settings`
) VALUES (
    '316bc6fc-a2af-47ed-910a-905f56c4bc63',
    'web-app',
    now(),
    NULL,
    NULL,
    'proxxy-store',
    'none',
    'refresh_token,authorization_code',
    'http://web-app.com/callback',
    'http://web-app.com/logout',
    'READ,WRITE',
    '{
        "@class": "java.util.Collections$UnmodifiableMap",
        "settings.client.require-proof-key": true,
        "settings.client.require-authorization-consent": true
    }',
    '{
        "@class": "java.util.Collections$UnmodifiableMap",
        "settings.token.reuse-refresh-tokens": true,
        "settings.token.x509-certificate-bound-access-tokens": false,
        "settings.token.id-token-signature-algorithm": [
            "org.springframework.security.oauth2.jose.jws.SignatureAlgorithm", "RS256"
        ],
        "settings.token.access-token-time-to-live": ["java.time.Duration", 25200.000000000],
        "settings.token.access-token-format": {
            "@class": "org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat",
            "value": "self-contained"
        },
        "settings.token.refresh-token-time-to-live": ["java.time.Duration", 36000.000000000],
        "settings.token.authorization-code-time-to-live": ["java.time.Duration", 300.000000000],
        "settings.token.device-code-time-to-live": ["java.time.Duration", 300.000000000]
    }'
);