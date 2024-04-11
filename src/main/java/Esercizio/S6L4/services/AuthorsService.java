package Esercizio.S6L4.services;


import Esercizio.S6L4.entities.Author;

import Esercizio.S6L4.exceptions.BadRequestException;
import Esercizio.S6L4.exceptions.NotFoundException;
import Esercizio.S6L4.payloads.NewAuthorDTO;
import Esercizio.S6L4.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    public Author save(NewAuthorDTO body) {
        authorsRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
        });
        Author newAuthor = new Author(body.name(),body.surname(),body.email(), body.dateOfBirth(), body.avatar(), "https://ui-avatars.com/api/?name=" + body.name().charAt(0) + "+" + body.surname().charAt(0));

        return authorsRepository.save(newAuthor);
    }

    public Page<Author> getAuthors(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return authorsRepository.findAll(pageable);
    }

    public Author findById(int id) {
        return authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        authorsRepository.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author body) {

        Author found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setDateOfBirth(body.getDateOfBirth());
        found.setAvatar(body.getAvatar());
        return authorsRepository.save(found);
    }
}
