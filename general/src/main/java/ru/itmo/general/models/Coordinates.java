package ru.itmo.general.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Coordinates implements Serializable {
        /**
         * Класс для координат
         */
        @JsonProperty("x")
        private Double x; // Значение поля должно быть больше -194, Поле не может быть null
        @JsonProperty("y")
        private double y; // Максимальное значение поля: 937

        // Приватный конструктор для использования в Builder
        private Coordinates(Builder builder) {
                this.x = builder.x;
                this.y = builder.y;
        }

        public Double getX() {
                return x;
        }

        public double getY() {
                return y;
        }

        @Override
        public String toString() {
                return "x: " + String.valueOf(x) + ", y: " + String.valueOf(y);
        }

        // Вложенный статический класс Builder
        public static class Builder {
                private Double x; // Значение поля должно быть больше -194, Поле не может быть null
                private double y; // Максимальное значение поля: 937

                public Builder setX(Double x) {
                        if (x == null || x <= -194) {
                                throw new IllegalArgumentException("x must be greater than -194 and cannot be null");
                        }
                        this.x = x;
                        return this;
                }

                public Builder setY(double y) {
                        if (y > 937) {
                                throw new IllegalArgumentException("y must be less than or equal to 937");
                        }
                        this.y = y;
                        return this;
                }

                public Coordinates build() {
                        if (this.x == null || this.x <= -194) {
                                throw new IllegalStateException("x must be set and greater than -194");
                        }
                        return new Coordinates(this);
                }
        }
}
