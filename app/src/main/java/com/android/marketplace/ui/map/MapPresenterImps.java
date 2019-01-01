package com.android.marketplace.ui.map;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;


import com.android.marketplace.data.model.Order;
import com.android.marketplace.data.model.Product;
import com.android.marketplace.data.source.OrderRepository;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Listens to user actions from {@link MapFragment}, retrieves the data and updates the
 * UI as required.
 * <p>
 * Created by ZARA on 12/23/2018.
 */
public class MapPresenterImps implements MapPresenter{

    private static final String TAG = MapPresenterImps.class.getName();
    private final MapFragmentView mMapView;
    private final OrderRepository mOrderRepository;


    @SuppressLint("RestrictedApi")
    @Inject
    public MapPresenterImps(MapFragmentView mapView,
                            OrderRepository orderRepository) {
        mMapView = checkNotNull(mapView, "mapView can not be null!");
        mOrderRepository = checkNotNull(orderRepository, "orderRepository can not be null!");
    }

    @Override
    public void saveAddress(Geocoder geocoder, GoogleMap map, Product product) {
        Order order = new Order();
        order.updateByProductDate(product);
        double lat = map.getCameraPosition().target.latitude;
        double lng = map.getCameraPosition().target.longitude;
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            order.updateByAddressesData(addresses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mOrderRepository.insertOrder(order);
    }

    @Override
    public void setDefaultLocationOnMap(GoogleMap googleMap) {
        // Set default location
        LatLng latLng = new LatLng(35.745578, 51.375443);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}
