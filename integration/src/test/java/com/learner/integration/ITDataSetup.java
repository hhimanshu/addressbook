package com.learner.integration;

import com.learner.persistence.harness.JpaRule;
import com.learner.persistence.harness.PreIntegration;
import com.learner.persistence.liquibase.DataSetupTest;
import liquibase.exception.LiquibaseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.SQLException;

@Category(PreIntegration.class)
public class ITDataSetup {
    @Rule
    public JpaRule jpaRule = new JpaRule(JpaRule.H2.directory, "unit-testing-pu");

    @Test
    public void testSetTestData() throws SQLException, LiquibaseException {
        System.out.println("setting up data");
        DataSetupTest.runLiquibaseUpdate(jpaRule);
    }
}
