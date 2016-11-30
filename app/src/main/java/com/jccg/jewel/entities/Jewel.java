package com.jccg.jewel.entities;

import io.realm.RealmObject;

/**
 *
 * @author Cristian Jaramillo (cristian_gerar@hotmail.com)
 */
public class Jewel extends RealmObject {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String material;

    /**
     *
     */
    private float price;

    /**
     *
     */
    private String description;

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getMaterial() {
        return material;
    }

    /**
     *
     * @param material
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     *
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
