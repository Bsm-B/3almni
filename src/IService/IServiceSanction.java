/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Amel
 */
public interface IServiceSanction<T , E, A> {
    void create(T t,E e, A a) throws SQLException;
    void delete(T t) throws SQLException;
    void update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
    List<T> sort()throws SQLException;
    List<T> searchById(int i)throws SQLException;
    List<T> searchByName(String s)throws SQLException;
}
