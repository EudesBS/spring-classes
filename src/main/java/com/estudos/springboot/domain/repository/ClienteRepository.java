package com.estudos.springboot.domain.repository;

import com.estudos.springboot.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);

    //HQL
    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    //SQL
    @Query(value = "select * from Cliente c where c.nome like '%:nome%'", nativeQuery = true)
    List<Cliente> acharPorNome(@Param("nome") String nome);

    //Sempre que for executar uma query de modiivação será necessário adicionar a annotation @Modifying
    @Query(" delete from Cliente c where c.nome = :nome ")
    @Modifying
    void deleteByName (String nome);

    List<Cliente> findByNomeOrId(String nome, Integer Id);

    boolean existsByNome(String nome);

    @Query(value = " select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


}
