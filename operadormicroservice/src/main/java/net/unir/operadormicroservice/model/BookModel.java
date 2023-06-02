/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unir.operadormicroservice.model;

import lombok.Data;

/**
 *
 * @author FABIAN MILLAN
 */
@Data
public class BookModel {

	private Long id;

	private String title;

	private String resume;

	private int stockQuantity;

	private int quantityRented;

	private String gender;

	private AuthorModel author;
}
