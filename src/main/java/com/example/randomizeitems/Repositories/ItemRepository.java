package com.example.randomizeitems.Repositories;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;

@Table(name="items")
public class ItemRepository{
}