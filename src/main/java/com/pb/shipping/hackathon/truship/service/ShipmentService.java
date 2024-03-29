package com.pb.shipping.hackathon.truship.service;

import com.pb.shipping.hackathon.truship.ext.CreateShipmentRequest;
import com.pb.shipping.hackathon.truship.model.Capacity;
import com.pb.shipping.hackathon.truship.model.Shipment;
import com.pb.shipping.hackathon.truship.repo.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    public Shipment createShipment(CreateShipmentRequest createShipmentRequest) {
        return shipmentRepository.save(convertTo(createShipmentRequest));
    }

    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public List<Shipment> getShipmentByShipperId(long shipperId) {
        return shipmentRepository.findAllByShipperId(shipperId);
    }

    private Shipment convertTo(CreateShipmentRequest createShipmentRequest) {
        Shipment shipment = new Shipment();
        shipment.setFromLocation(createShipmentRequest.getFromLocation());
        shipment.setToLocation(createShipmentRequest.getToLocation());
        shipment.setShipmentDate(createShipmentRequest.getShipmentDate());
        shipment.setShipmentTime(createShipmentRequest.getShipmentTime());
        shipment.setShipperId(createShipmentRequest.getShipperId());
        shipment.setCapacity(new Capacity(createShipmentRequest.getVolume(), createShipmentRequest.getWeight()));
        shipment.setRegNumber(createShipmentRequest.getRegNumber());
        return shipment;
    }
}
