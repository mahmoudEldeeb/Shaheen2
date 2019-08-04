package com.g2m.shaheen.models.dataModels.ResultModel;

import com.g2m.shaheen.models.dataModels.CategoryModel;
import com.g2m.shaheen.models.dataModels.ProductModel;
import com.g2m.shaheen.models.dataModels.StockModel;

import java.util.List;

public class ProductDataResult {
    private List<StockModel>stock;
    private List<ProductModel>product;

    private List<CategoryModel>category;

    public List<CategoryModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryModel> category) {
        this.category = category;
    }

    public List<StockModel> getStock() {
        return stock;
    }

    public List<ProductModel> getProduct() {
        return product;
    }

    public void setStock(List<StockModel> stock) {
        this.stock = stock;
    }

    public void setProduct(List<ProductModel> product) {
        this.product = product;
    }
}
