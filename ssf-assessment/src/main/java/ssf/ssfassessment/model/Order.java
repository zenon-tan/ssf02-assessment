package ssf.ssfassessment.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Order implements Serializable {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be minimum 3 characters")
    private String name;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{8}$", message = "Please enter an 8-digit phone number")
    private String phone;

    private String isRush = "false";
    private String comments;
    private String orderId;
    private String type;
    private String size;
    private int quantity;
    private float totalCost;

    public Order() {

        this.orderId = generateId(8);

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getIsRush() {
        return isRush;
    }
    public void setIsRush(String isRush) {
        if (isRush == null) {
            isRush = "false";
        }
        this.isRush = isRush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


    private synchronized String generateId(int numOfChar) {

        Random rand = new Random();
        StringBuilder strBuilder = new StringBuilder();

        while(strBuilder.length() < numOfChar) {
            int nextVal = rand.nextInt();
            strBuilder.append(Integer.toHexString(nextVal));
        }

        return strBuilder.toString().substring(0, numOfChar);
    }

    @Override
    public String toString() {
        return "Order [name=" + name + ", address=" + address + ", phone=" + phone + ", isRush=" + isRush
                + ", comments=" + comments + ", orderId=" + orderId + ", type=" + type + ", size=" + size
                + ", quantity=" + quantity + ", totalCost=" + totalCost + "]";
    }


  
}
