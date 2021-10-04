package com.practice.springWebApp.bootstrap;

import com.practice.springWebApp.domain.Author;
import com.practice.springWebApp.domain.Book;
import com.practice.springWebApp.domain.Publisher;
import com.practice.springWebApp.repositories.AuthorRepository;
import com.practice.springWebApp.repositories.BookRepository;
import com.practice.springWebApp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("test", "address1", "Melbourne", "Victoria", "3030");
        publisherRepository.save(publisher);
        System.out.println("number of publishers = " + publisherRepository.count());


        Author eric = new Author("Eric", "Evans");
        Book domainDrivenDesign = new Book("Domain Driven Design", "12345");
        eric.getBooks().add(domainDrivenDesign);
        domainDrivenDesign.getAuthors().add(eric);

        domainDrivenDesign.setPublisher(publisher);
        publisher.getBooks().add(domainDrivenDesign);


        authorRepository.save(eric);
        bookRepository.save(domainDrivenDesign);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE", "12221");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("started Bootstrap");
        System.out.println("number of books = " + bookRepository.count());



        System.out.println("publisher num of books = " + publisher.getBooks().size());

    }
}
