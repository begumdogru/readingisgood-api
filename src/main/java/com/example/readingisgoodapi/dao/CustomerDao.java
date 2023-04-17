package com.example.readingisgoodapi.dao;

import com.example.readingisgoodapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class CustomerDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<Customer> getAllCustomer() {
        String sql = "SELECT * FROM customer";
        try {
            return jdbcTemplate.query(sql, new CustomerMapper());
        } catch (DataAccessException ex) { // Spring DataAccessException kullanarak daha özel bir istisna türü kullanabiliriz
            // Add appropriate error handling
            ex.printStackTrace(); // ya da hatayı uygun bir şekilde loglayabiliriz
            throw new RuntimeException("An error occurred while retrieving customers.", ex); // ya da uygun bir özel istisna sınıfını fırlatabiliriz
        }
    }


    public Customer getCustomer(int id) {
        String sql = "select * from customer where cid = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerMapper());
    }

    public static class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCid((long) rs.getInt("cid"));
            customer.setCname(rs.getString("cname"));
            customer.setPhone(rs.getString("phone"));
            return customer;
        }

    }
    public boolean saveCustomer(Customer customer) {
        String sql = "INSERT INTO customer (cid, cname, phone) VALUES (?,?,?)";
        int result = jdbcTemplate.update(sql, customer.getCid(), customer.getCname(), customer.getPhone());

        return result > 0;
    }
        public boolean updateCustomer(Customer customer) {
        String sql = "update customer set cname=?, phone=? where cid=?";
        int value = jdbcTemplate.update(sql, new Object[]{customer.getCname(), customer.getPhone(), customer.getCid()});
        if (value > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        String sql = "delete from customer where cid=?";
        int value = jdbcTemplate.update(sql, new Object[]{id});
        if (value > 0) {
            return true;
        }
        return false;
    }

}
