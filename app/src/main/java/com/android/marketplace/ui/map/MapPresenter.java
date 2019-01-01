package com.android.marketplace.ui.map;


import android.location.Geocoder;

import com.android.marketplace.data.model.Product;
import com.google.android.gms.maps.GoogleMap;

/**
 *
 * To handle all user actions that are related to {@link MapFragment}
 *
 * Created by ZARA on 12/23/2018.
 */
public interface MapPresenter {

    /**
     * To save address(City, State, Zip, Country, ...)
     * in {@link com.android.marketplace.data.model.Order}
     * by geocoder and map
     *  @param geocoder
     * @param map
     * @param product
     */
    void saveAddress(Geocoder geocoder, GoogleMap map, Product product);

    /**
     * To set default location on {@link GoogleMap}
     *
     * @param googleMap
     */
    void setDefaultLocationOnMap(GoogleMap googleMap);
}
