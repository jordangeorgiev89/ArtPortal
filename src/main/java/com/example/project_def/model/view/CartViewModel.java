package com.example.project_def.model.view;

import java.util.List;

public class CartViewModel extends BaseViewModel {

    private List<ProductViewModel> productsViewModel;

    public CartViewModel() {
    }

    public List<ProductViewModel> getProductsViewModel() {
        return productsViewModel;
    }

    public void setProductsViewModel(List<ProductViewModel> productsViewModel) {
        this.productsViewModel = productsViewModel;
    }
}
