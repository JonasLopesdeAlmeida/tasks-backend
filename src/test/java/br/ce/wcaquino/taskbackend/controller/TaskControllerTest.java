package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskrepo;
	
	//essa anotacao faz com que o mock definido no Taskrepo seja injetado no controller
	@InjectMocks
	private TaskController controller;
	
	//esse metodo sera inicializado antes de cada teste
	//ele vai olhar as anotacoes que referenciam o MOCKITO
	//dessa forma ele vai criar todos os mocks que estao definidos, ele vai fazer uma instancia do controller
	//e vai injetar os mocks criados no taskrepo dentro do controller.
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Test
	public void naoDeveSalvarTarefasSemDescricao() {

		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
		    fail("Nao deveria chegar nesse ponto!");
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
			e.printStackTrace();
		}

	}

	@Test
	public void naoDeveSalvarTarefasSemData() {

		Task todo = new Task();
		todo.setTask("Descricao");
		// todo.setDueDate(LocalDate.now());
		

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
			e.printStackTrace();
		}

	}

	@Test
	public void naoDeveSalvarTarefasComDataPassada() {

		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.of(2010, 01, 01));
		

		try {
			controller.save(todo);
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
			e.printStackTrace();
		}

	}

	@Test
	public void deveSalvarTarefasComSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		//verifica se realmente o taskrepo esta salvando o todo.
		Mockito.verify(taskrepo).save(todo);

	}

}
