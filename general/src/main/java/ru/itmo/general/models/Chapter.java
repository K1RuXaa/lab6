package ru.itmo.general.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Chapter implements Serializable {
    /**
     * Класс Chapter
     */
    @JsonProperty("chapterName")
    private String name; // Поле не может быть null, Строка не может быть пустой
    @JsonProperty("parentLegion")
    private String parentLegion;

    private Chapter(Builder builder) {
        this.name = builder.name;
        this.parentLegion = builder.parentLegion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }

    @Override
    public String toString() {
        return "\n\tname: " + name + "\n\tparentLegion: " + parentLegion;
    }

    public static class Builder {
        private String name; // Поле не может быть null, Строка не может быть пустой
        private String parentLegion;

        public Builder setName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("name cannot be null or empty");
            }
            this.name = name;
            return this;
        }

        public Builder setParentLegion(String parentLegion) {
            this.parentLegion = parentLegion;
            return this;
        }

        public Chapter build() {
            if (this.name == null || this.name.isEmpty()) {
                throw new IllegalStateException("name cannot be null or empty");
            }
            return new Chapter(this);
        }
    }
}
