package com.fa.CouponsMsProject.utils.generators;

import com.fa.CouponsMsProject.beans.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CompanyNameGenerator {

    public static String generateName(Category profile) {
        int rand = new Random().nextInt(2);
        switch (profile) {
            case HOTELS:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.TEMPEST_HOTELS);
                    case 1:
                        return convertName(AvailableCompanyNames.TALISMAN_HOTELS);
                }
            case RESTAURANT:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.RESTAURANT_VENUE);
                    case 1:
                        return convertName(AvailableCompanyNames.CLOUD_TASTE);
                }
            case PERSONAL_CARE:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.CARE_SHOP);
                    case 1:
                        return convertName(AvailableCompanyNames.TRUE_CARE);
                }
            case ELECTRONICS:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.NEBULA_ELECTRONICS);
                    case 1:
                        return convertName(AvailableCompanyNames.ELECTRONICS_SUPPLY);
                }
            case TOOLS:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.PIONEER_TOOLS);
                    case 1:
                        return convertName(AvailableCompanyNames.TOOL_SCOUT);
                }
            case AUTOMOTIVE:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.CHEETAH_AUTOMOTIVE);
                    case 1:
                        return convertName(AvailableCompanyNames.AUTOMOTIVE_CANDY);
                }
            case FASHION:
                switch (rand) {
                    case 0:
                        return convertName(AvailableCompanyNames.FASHION_EAGLE);
                    case 1:
                        return convertName(AvailableCompanyNames.OUTFISTA);
                }

        }
        return "EMPTY CASE";
    }

    private static String convertName(AvailableCompanyNames name) {
        StringBuilder nameBuilder = new StringBuilder();
        List<String> arr = new ArrayList<>(Arrays.asList(name.toString().replaceAll("_", " ").toLowerCase().split(" ")));
        for (String str : arr) {
            nameBuilder.append(str.substring(0, 1).toUpperCase());
            nameBuilder.append(str.substring(1));
            nameBuilder.append(" ");
        }
        return nameBuilder.toString().trim();
    }
}
