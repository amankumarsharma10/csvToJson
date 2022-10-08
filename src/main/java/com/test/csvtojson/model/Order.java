package com.test.csvtojson.model;

import lombok.ToString;

@ToString
public class Order {
    private String orderId;
    private String amount;
    private String currency;
    private String fileName;
    private Long lineNumber;
    private String comments;

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.fileName = builder.fileName;
        this.lineNumber=builder.lineNumber;
        this.comments = builder.comments;
    }

    public static class OrderBuilder {
        private String orderId;
        private String amount;
        private String currency;

        private String fileName;

        private Long lineNumber;
        private String comments;

        public OrderBuilder() {

        }

        public OrderBuilder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public OrderBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public OrderBuilder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public OrderBuilder lineNumber(Long lineNumber) {
            this.lineNumber = lineNumber;
            return this;
        }
        public OrderBuilder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Order build() {
            Order order = new Order(this);
            return order;
        }
    }
}
