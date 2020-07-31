package com.lambdaschool.anywherefitness.repository;

import com.lambdaschool.anywherefitness.models.Todos;
import org.springframework.data.repository.CrudRepository;

public interface TodosRepository extends CrudRepository<Todos, Long>
{
}
