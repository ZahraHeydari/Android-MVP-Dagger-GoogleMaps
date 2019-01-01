package com.android.marketplace.ui.map;


import android.content.Context;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.marketplace.R;
import com.android.marketplace.data.model.Product;
import com.android.marketplace.ui.main.OnMainActivityCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


import org.parceler.Parcels;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;



public class MapFragment extends DaggerFragment implements MapFragmentView, OnMapReadyCallback {

    public static final String FRAGMENT_NAME = MapFragment.class.getSimpleName();
    private static final String TAG = MapFragment.class.getName();
    private GoogleMap mMap;
    private View root;
    @BindView(R.id.map_confirm_address_marker_image_view)
    ImageView mConfirmAddressMarkerImageView;
    @Inject
    MapPresenter mPresenter;
    @Inject
    Geocoder geocoder;
    private OnMainActivityCallback mCallback;
    private Product mProduct;

    @OnClick(R.id.map_address_submit_button)
    void mAddressSubmitButton(){
        if (mMap == null) return;
        mPresenter.saveAddress(geocoder,mMap,mProduct);
        mCallback.navigateToOrderPage();
    }

    public static MapFragment newInstance(Product product) {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        args.putParcelable(Product.class.getName(),Parcels.wrap(product));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainActivityCallback) {
            mCallback = (OnMainActivityCallback) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnMainActivityCallback!");
        }

        // get product item
        if(getArguments()!=null && getArguments().containsKey(Product.class.getName())){
            mProduct = Parcels.unwrap(getArguments().getParcelable(Product.class.getName()));
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, root);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return root;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mPresenter.setDefaultLocationOnMap(googleMap);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        root = null;
        mCallback = null;
    }
}
