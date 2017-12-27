package com.ipman1971.packlink.repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by jcorredera on 22/12/17.
 */
public interface IGenericRepository<KEY, ENTITY> {

    ENTITY findOne(final KEY key);

    List<ENTITY> findAll();

    void create(final KEY key, final ENTITY entity, boolean active);

    ENTITY update(final KEY key, final ENTITY entity, boolean active);

    void delete(final KEY key);

}
