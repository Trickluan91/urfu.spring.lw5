package ru.trickluan.spring.lw5.service;

import org.springframework.stereotype.Service;
import ru.trickluan.spring.lw5.model.Positions;

@Service
public class QuarterBonusServiceImpl implements QuarterBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {
        return positions.isManager() ? (salary * bonus * 91.25 * positions.getPositionCoefficient() / workDays) : 0;
    }
}
