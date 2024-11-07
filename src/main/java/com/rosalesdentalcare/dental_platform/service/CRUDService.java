package com.rosalesdentalcare.dental_platform.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService <T, ID> {

    public List<T> list();
    public Optional<T> getOne(ID id);
    public void save(T obj);
    public void delete(ID id);
    public boolean existsById(ID id);
}
