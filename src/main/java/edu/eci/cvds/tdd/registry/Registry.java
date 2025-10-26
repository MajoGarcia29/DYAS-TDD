package edu.eci.cvds.tdd.registry;

import java.util.HashSet;
import java.util.Set;

public class Registry {

    private final Set<Integer> registeredIds = new HashSet<>();

    // Constantes (calidad de código)
    public static final int MIN_AGE = 0;
    public static final int VOTING_AGE = 18;
    public static final int MAX_AGE = 120;

    public RegisterResult registerVoter(Person p) {
        if (p == null) return RegisterResult.INVALID;                // defensa
        if (!p.isAlive()) return RegisterResult.DEAD;                 // muerto no vota
        if (p.getId() <= 0) return RegisterResult.INVALID;           // id inválido

        int age = p.getAge();
        if (age < MIN_AGE || age > MAX_AGE) return RegisterResult.INVALID_AGE;
        if (age < VOTING_AGE) return RegisterResult.UNDERAGE;

        if (registeredIds.contains(p.getId())) return RegisterResult.DUPLICATED;

        registeredIds.add(p.getId());
        return RegisterResult.VALID;
    }

    // añadido para tests (limpiar estado entre ellos)
    public void clearRegistry() {
        registeredIds.clear();
    }
}
