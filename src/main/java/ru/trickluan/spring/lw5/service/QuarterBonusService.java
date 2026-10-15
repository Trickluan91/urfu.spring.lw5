package ru.trickluan.spring.lw5.service;

import ru.trickluan.spring.lw5.model.Positions;

public interface QuarterBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
