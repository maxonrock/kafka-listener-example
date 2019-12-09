package ru.sber.dev.models.db.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.sber.dev.models.db.PersistedMessage;

@Repository
public interface PersistedMessageRepository extends CrudRepository<PersistedMessage, Long> {
}
