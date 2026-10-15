package ru.trickluan.spring.lw5.service;

import org.junit.jupiter.api.Test;
import ru.trickluan.spring.lw5.model.Positions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuarterBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.CTO;
        double bonus = 1.9;
        int workDays = 66;
        double salary = 150_000.0;

        double result = new QuarterBonusServiceImpl().calculate(position, salary, bonus, workDays);

        double expected = 906_278.409090909;
        assertEquals(expected,result,0);
    }
}