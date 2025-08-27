package org.banco_abc.client;

public record ChangeTypeResponse(
        Double compra,
        Double venta,
        Double sunat,
        String fecha
) {}
