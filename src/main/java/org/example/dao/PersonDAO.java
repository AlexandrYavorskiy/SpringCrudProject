package org.example.dao;

import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;



    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() throws SQLException {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
/*    private List<Person> people = new ArrayList<>();*/

/*    {
        people.add(new Person(++PEOPLE_COUNT, "Sally", 43, "Sally@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bobby", 23, "Bobby@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Tim", 27, "Tim@mail.com"));
    }*/

/*    public List<Person> index(){
  *//*      return people;*//*
        return null;
    }*/

    public Person show(int id) throws SQLException {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
 /*       for (int i=0; i<people.size(); i++){
            if (people.get(i).getId()==id){
                return people.get(i);
            }
        }*/
/*        Person person = new Person();

        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM Person WHERE id=?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;*/
        //return people.stream().filter(people -> people.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) throws SQLException {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());

/*        PreparedStatement preparedStatement =
                 connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?");

        preparedStatement.setString(1,person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());

        preparedStatement.executeUpdate();*/
/*        person.setId(++PEOPLE_COUNT);
        people.add(person);*/
    }

    public void update(int id, Person updatedPerson) throws SQLException {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?"
                , updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
/*        PreparedStatement preparedStatement =
                connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");



        preparedStatement.setString(1,updatedPerson.getName());
        preparedStatement.setInt(2, updatedPerson.getAge());
        preparedStatement.setString(3, updatedPerson.getEmail());
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();*/
 /*       Person updPerson = show(id);
        updPerson.setName(updatedPerson.getName());
        updPerson.setAge(updatedPerson.getAge());
        updPerson.setEmail(updatedPerson.getEmail());*/
    }

    public void delete(int id) throws SQLException {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

/*        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM Person WHERE id=?");

        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();*/
/*        for (int i=0; i<people.size(); i++){
            if (people.get(i).getId()==id){
                people.remove(i);
                break;
            }
        }*/
    }
}
