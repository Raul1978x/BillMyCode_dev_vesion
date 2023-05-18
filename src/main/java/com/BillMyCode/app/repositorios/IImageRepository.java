package com.BillMyCode.app.repositorios;

import com.BillMyCode.app.entidades.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
}
