package service;

import model.Delivery;

public class DeliveryServiceImpl implements DeliveryService {
    private static DeliveryServiceImpl INSTANCE;
    protected final int MAX_VALUE = 5_000;

    private DeliveryServiceImpl() {
    }

    public static DeliveryServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeliveryServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public Delivery getByAddress(String address) {
        long cost = Math.abs(address.hashCode() % MAX_VALUE);
        return new Delivery(address, cost);
    }
}
