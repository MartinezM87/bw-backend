package com.lambdaschool.anywherefitness.services;

import com.lambdaschool.anywherefitness.models.Todos;
import com.lambdaschool.anywherefitness.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService
{
    @Autowired
    private TodosRepository todosrepos;

    @Override
    public List<Todos> findAll()
    {
        List<Todos> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        todosrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Todos save(Todos todo)
    {
        Todos newTodo = new Todos();

        if (todo.getTodoid() != 0)
        {
            todosrepos.findById(todo.getTodoid())
                    .orElseThrow(() -> new EntityNotFoundException("Todo " + todo.getTodoid() + " Not Found! "));
            newTodo.setTodoid(todo.getTodoid());
        }

        newTodo.setTodoid(todo.getTodoid());
        newTodo.setDescription(todo.getDescription());
        newTodo.setCompleted(todo.isCompleted());
        newTodo.setUser(todo.getUser());

        return todosrepos.save(newTodo);
    }

    @Override
    public Todos update(long id)
    {
        Todos currentTodo = todosrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo " + id + " Not Found! "));


        currentTodo.setCompleted(true);


        return todosrepos.save(currentTodo);
    }

    @Override
    public void delete(long id)
    {
        todosrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo id " + id + " Not Found! "));
        todosrepos.deleteById(id);
    }

}
