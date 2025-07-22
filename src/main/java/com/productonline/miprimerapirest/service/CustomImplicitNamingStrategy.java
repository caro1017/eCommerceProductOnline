package com.productonline.miprimerapirest.service;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class CustomImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
    @Override
    protected Identifier toIdentifier(@org.jetbrains.annotations.NotNull String stringForm, MetadataBuildingContext buildingContext) {
        return super.toIdentifier(stringForm.toUpperCase(), buildingContext);
    }
}
