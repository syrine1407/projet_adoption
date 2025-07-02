package com.example.adoptionproject.services;

import com.example.adoptionproject.entities.Adoptant;
import com.example.adoptionproject.entities.Animal;
import com.example.adoptionproject.entities.Adoption;
import com.example.adoptionproject.repositories.AdoptantRepository;
import com.example.adoptionproject.repositories.AnimalRepository;
import com.example.adoptionproject.repositories.AdoptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdoptionServicesImplTest {

    @Mock
    private AdoptantRepository adoptantRepository;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AdoptionRepository adoptionRepository;

    @InjectMocks
    private AdoptionServicesImpl adoptionServices;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAdoptant() {
        Adoptant adoptant = new Adoptant();
        adoptant.setNom("Syrine");

        when(adoptantRepository.save(any(Adoptant.class))).thenReturn(adoptant);

        Adoptant result = adoptionServices.addAdoptant(adoptant);

        assertNotNull(result);
        assertEquals("Syrine", result.getNom());

        verify(adoptantRepository).save(adoptant);
    }

    @Test
    void testAddAdoption_Success() {
        Adoptant adoptant = new Adoptant();
        adoptant.setIdAdoptant(1);
        adoptant.setNom("Syrine");

        Animal animal = new Animal();
        animal.setIdAnimal(2);
        animal.setNom("Chat");

        Adoption adoption = new Adoption();
        adoption.setFrais(100f);

        when(adoptantRepository.findById(1)).thenReturn(Optional.of(adoptant));
        when(animalRepository.findById(2)).thenReturn(Optional.of(animal));
        when(adoptionRepository.save(any(Adoption.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Adoption result = adoptionServices.addAdoption(adoption, 1, 2);

        assertNotNull(result);
        assertEquals(adoptant, result.getAdoptant());
        assertEquals(animal, result.getAnimal());
        assertEquals(100f, result.getFrais());

        verify(adoptantRepository).findById(1);
        verify(animalRepository).findById(2);
        verify(adoptionRepository).save(adoption);
    }
}