package ru.trickluan.spring.lw5.service;

import org.springframework.stereotype.Service;
import ru.trickluan.spring.lw5.model.Positions;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays, boolean leapYear) {
        return salary * bonus * (leapYear ? 366 : 365 ) * positions.getPositionCoefficient() / workDays;
    }
}
