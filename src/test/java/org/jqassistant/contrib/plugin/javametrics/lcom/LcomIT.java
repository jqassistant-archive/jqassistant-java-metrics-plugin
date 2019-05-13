package org.jqassistant.contrib.plugin.javametrics.lcom;

import static org.junit.Assert.assertEquals;

import com.buschmais.jqassistant.core.analysis.api.Result;
import com.buschmais.jqassistant.core.analysis.api.rule.Concept;
import com.buschmais.jqassistant.core.analysis.api.rule.Severity;
import org.jqassistant.contrib.plugin.javametrics.AbstractMetricsIT;
import org.jqassistant.contrib.plugin.javametrics.oodmetrics.AbstractnessIT;
import org.junit.Test;

public class LcomIT extends AbstractMetricsIT {

    @Test
    public void whenApplyLcom_expectValue() throws Exception {
        // arrange
        scanClassPathDirectory(getClassesDirectory(AbstractnessIT.class));

        // act
        Result<Concept> result = applyConcept("lcom-metrics:LcomCountGroups");

        // assert
        assertEquals(Result.Status.SUCCESS, result.getStatus());
        assertEquals(Severity.MINOR, result.getSeverity());

        store.beginTransaction();
        assertEquals(Long.valueOf(1), retrieveSingleValue("MATCH (c:Java:Class {fqn:\"org.jqassistant.contrib.plugin.javametrics.lcom.a.Clazz1\"}) RETURN c.lcom", Long.class));
        store.commitTransaction();
    }

}
