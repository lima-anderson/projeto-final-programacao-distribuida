package com.pdist.msemail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdist.msemail.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
