package com.ishnit.repository;

import com.ishnit.entity.Author;
import com.ishnit.entity.Book;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {

}
