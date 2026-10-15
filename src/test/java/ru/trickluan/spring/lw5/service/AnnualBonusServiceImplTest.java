package ru.trickluan.spring.lw5.service;

import org.junit.jupiter.api.Test;
import ru.trickluan.spring.lw5.model.Positions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100_000.0;

        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays, true);

        double expected = 361_481.48148148146;
        assertEquals(expected,result,0);
    }
}