package com.proxxy.store.core.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {


    @interface Product {

        @PreAuthorize("isAuthenticated() and hasAuthority('EDIT_PRODUCT')")
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @interface Edit {
        }

        @PreAuthorize("isAuthenticated()")
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @interface Consult {
        }
    }

    @interface Category {

        @PreAuthorize("isAuthenticated() and hasAuthority('EDIT_CATEGORY')")
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @interface Edit {
        }

        @PreAuthorize("isAuthenticated()")
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.RUNTIME)
        @interface Consult {
        }
    }
}
