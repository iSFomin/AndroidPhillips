package com.bignerdranch.android.criminalintent.other;

import android.content.Context;

import com.bignerdranch.android.criminalintent.Crime;
import com.bignerdranch.android.criminalintent.CrimeNotFoundException;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    private SortedMap<UUID, Integer> mIndexMapCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    private CrimeLab() {
        mCrimes = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            if (i == 10) {
                crime.setTitle("Long long long long long long long long long long long crime #" + i);
            } else {
                crime.setTitle("Crime #" + i);
            }
            crime.setSolved(i % 2 == 0);

            mCrimes.add(crime);
        }

        createIndexMap(mCrimes);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
        createIndexMap(mCrimes);
    }

    public void updateCrime(Crime c) {

    }

    public void deleteCrime(Crime c) {
        mCrimes.remove(c);
        createIndexMap(mCrimes);
    }

    public Crime getCrime(UUID id) throws CrimeNotFoundException {
        Integer idx = mIndexMapCrimes.get(id);

        if (idx != null) {
            return mCrimes.get(idx);
        }

        throw new CrimeNotFoundException();
    }

    public Crime getFirstCrime() {
        return mCrimes.get(0);
    }

    public int getIndex(UUID id) {
        Integer idx = mIndexMapCrimes.get(id);
        if (idx == null) return 0;
        return idx;
    }

    private void createIndexMap(List<Crime> crimes) {
        mIndexMapCrimes = new TreeMap<>();

        for (int i = 0; i < crimes.size(); i++) {
            Crime crime = crimes.get(i);
            mIndexMapCrimes.put(crime.getId(), i);
        }
    }
}
