package br.com.aula.managementms.service.impl;

import br.com.aula.managementms.dto.ManagementDTO;
import br.com.aula.managementms.repository.ManagementRepository;
import br.com.aula.managementms.service.ManagementService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"classpath:populate-database.sql"})
class ManagementServiceImplTest {

  @Autowired
  private ManagementService managementService;

  @Autowired
  private ManagementRepository managementRepository;

  @BeforeAll
  private static void setUp() {
    FixtureFactoryLoader.loadTemplates("br.com.aula.managementms.fixture");
  }

  @Test
  public void shouldCreateManagement() {
    ManagementDTO request = Fixture.from(ManagementDTO.class).gimme("valid");
    Optional<ManagementDTO> response = managementService.create(request);

    assertNotNull(response.get());
    assertNotNull(request.getName(), response.get().getName());
    assertNotNull(request.getTasks(), response.get().getTasks().toString());
  }

  @Test
  public void shouldUpdateManagement() {
    ManagementDTO request = Fixture.from(ManagementDTO.class).gimme("valid-update");
    Optional<ManagementDTO> response = managementService.get(99);
    Optional<ManagementDTO> updated = managementService.update(request, 99);

    MatcherAssert.assertThat(response.get().getName(), CoreMatchers.is(Matchers.not(updated.get().getName())));
    assertEquals(request.getName(), updated.get().getName());

  }

  @Test
  public void shouldNotUpdateManagement() {
    ManagementDTO request = Fixture.from(ManagementDTO.class).gimme("valid-update");
    Optional<ManagementDTO> updated = managementService.update(request, 999);

   assertNotNull(updated);

  }


  @Test
  public void shouldGetManagement() {
    Optional<ManagementDTO> response = managementService.get(99);

    assertNotNull(response.get());
  }

  @Test
  public void shouldNotGetManagement() {
    Optional<ManagementDTO> response = managementService.get(999);

    assertNotNull(response);
  }


  @Test
  public void shouldFindAllManagement() {

    Optional<List<ManagementDTO>> responses = managementService.getAll();

    assertNotNull(responses.get());
  }

  @Test
  public void shouldDeleteManagement() {

    boolean response = managementService.delete(99);

    assertTrue(response);
  }
  @Test
  public void shouldNotDeleteManagement() {

    boolean response = managementService.delete(999);

    assertFalse(response);
  }


}