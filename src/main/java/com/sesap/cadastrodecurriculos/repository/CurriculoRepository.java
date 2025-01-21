package com.sesap.cadastrodecurriculos.repository;

import com.sesap.cadastrodecurriculos.entity.Curriculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculoRepository extends JpaRepository<Curriculo,Long> {
}
