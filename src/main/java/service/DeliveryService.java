package service;

import model.Delivery;

public interface DeliveryService {
    Delivery getByAddress(String address);
}
