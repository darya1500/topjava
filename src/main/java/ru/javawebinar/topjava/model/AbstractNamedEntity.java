package ru.javawebinar.topjava.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    protected String name;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}