package ru.trickluan.spring.lw5.service;

import org.springframework.stereotype.Service;
import ru.trickluan.spring.lw5.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays, boolean leapYear);
}
