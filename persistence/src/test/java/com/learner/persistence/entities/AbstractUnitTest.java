package com.learner.persistence.entities;

import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.harness.JpaRule;
import org.junit.Before;
import org.junit.Rule;

public class AbstractUnitTest {
	@Rule
	public JpaRule jpaRule = new JpaRule("unit-testing-pu");
	protected CrudService crudService;

	@Before
	public void setUp() {
		crudService = jpaRule.createCrudService(CrudService.class);
	}
}
