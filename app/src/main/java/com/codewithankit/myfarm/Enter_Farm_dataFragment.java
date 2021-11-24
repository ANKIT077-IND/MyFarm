package com.codewithankit.myfarm;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 */
public class Enter_Farm_dataFragment extends Fragment {
   Spinner land_ownership,tillage,tractor,own,on_lease,bullock,watering_soil,water_lease,water_tubewell,transportation_cost,motorised,transportatonbullock,
           manure,fertilizermanure,home_Produced,cow_per_season,waterrequired,weteronlease,wetertubewell,fertilizertransportcost,fertilizermotorised,fertilizerbullock,manurelabour,manurefertilizer,
           plantation,seeds,quantityofseeds,plantation_water_required,plantationwaterservices,plantation_tubewell,plantation_transportationcost,plantation_motorised,plantation_bullock,plantation_labour,
           plant_care,fungicide,plantcarequantity_of_seeds,plant_carewaterrequired,plantcarewaterservice,plantcaretubewell,plantCaretransportationcost,plantCaremotorised,plantCarebullock,plantCarePeriodic_irrigation,plantCareweeding,
           harvesting,harvestingtype,self,manual,mechanical,lease,lease_manual,lease_mechanical;
   String  Land_ownership,Tillage,Tractor,Own,On_lease,Bullock,Watering_soil,Water_lease,Water_tubewell,Transportation_cost,Motorised,Transportatonbullock,
           Manure,fertilizerManure,Home_produced,Cow_per_season,Waterrequired,Weteronlease,weterTubewell,Fertilizertransportcost,Fertilizermotorised,Fertilizerbullock,Manurelabour,Manurefertilizer,
           Plantation,Seeds,Quantityofseeds,Plantation_Water_required,Plantationwaterservices,Plantation_tubewell,Plantation_transportationCost,Plantation_motorised,Plantation_bullock,Plantation_labour,
           Plant_care,Fungicide,Plantcarequantity_of_seeds,Plant_carewaterrequired,plantcareWaterservice,Plant_caretubewell,Plantcaretransportationcost,Plantcaremotorised,PlantCarebullock,PlantcarePeriodic_irrigation,Plantcareweeding,
           Harvesting,Harvestingtype,Self,Manual,Mechanical,Lease,Lease_manual,Lease_mechanical;

   String Land_Ownership[]={"Tillage","Watering Soil","Transportation Cost"};
   String TIllage[]={"Tractor","Bullock"};
   String TRactor[]={"Own","On Lease","Tillage fixed contract price per acre"};
   String OWN[]={"Cost of Tractor","Cost of fuel","Cost of equipment"};
   String On_Lease[]={"Cost of tillage per acre"};
   String BUllock[]={"No of hours Bull works in a day","Cost of feeding Bull per day","Cost of equipment per season"};
   String Watering_Soil[]={"Water service on lease","Water from Tube well"};
   String Water_Lease[]={"No of hours & day water required","Cost of water per hour/acre"};
   String Water_Tubewell[]={"Cost of Electricity/hr","No of hours & day to moisten the soil","Cost of equipment"};
   String Transportation_Cost[]={"Motorised","Bullock"};
   String MOtorised[]={"Cost of Vehicle","No of rounds to farmland and Cost of fuel","Vehicle maintenance"};
   String transportatonBullock[]={"Distance walked by bull","Cost of maintain bull health and feeding"};
   String MAnure[]={"Manure","Water required in manure & fertilizer application","Transportation Cost","Labour for Manure Application","Fertilizer"};
   String FertilizerManure[]={"Home produced Manure per animal","Quantity of Manure required per acre ","Quantity of manure & cost per season"};
   String Home_Produced[]={"No of Buffaloes & cows","Expenses for maintaining one cow per season","Cost of buying a buffalo"};
   String Cow_Per_Season[]={"Medical Charges","Feed cost","Water required","Labour to look after"};
   String WaterRequired[]={"Water service on lease","Water from Tube well"};
   String WeterOnlease[]={"No of hours & day water required per acre","Cost of water per hour/acre"};
   String WeterTubewell[]={"Cost of Electricity/hr","No of hours & day to moisten the soil","Cost of equipment"};
   String FertilizerTransportCost[]={"Motorised","Bullock"};
   String FertilizerMotorised[]={"Cost of Vehicle","No of rounds to farmland and Cost of fuel","Vehicle maintenance"};
   String FertilizerBullock[]={"Distance walked by bull","Cost of maintain bull health and feeding"};
   String ManureLabour[]={"No of labour required per acre","Cost of Labour in a day","Cost of equipment per season"};
   String ManureFertilizer[]={"Types of fertilizers used","Cost of Fertilizer per bag","No of bags bought","Subsidies on fertilizers"};
   String PLantation[]={"Seeds","Water required in manure & fertilizer application","Transportation Cost","Plantation labour"};
   String SEeds[]={"Quantity of seeds re-utilised","Quantity of seeds required per acre ","cost buying seed season","Grading / Cleaning of seeds"};
   String QuantityOfseeds[]={"Seeds actually used","Seeds wasted"};
   String Plantation_Water_Required[]={"Water service on lease","Water from Tube well"};
   String PlantationWaterservices[]={"No of hours & day water required per acre","Cost of water per hour/acre"};
   String Plantation_Tubewell[]={"Cost of Electricity/hr","No of hours & day to moisten the soil","Cost of equipment"};
   String Plantation_TransportationCost[]={"Motorised","Bullock"};
   String Plantation_Motorised[]={"Cost of Vehicle","No of rounds to farmland and Cost of fuel","Vehicle maintenance"};
   String Plantation_Bullock[]={"Distance walked by bull","Cost of maintain bull health and feeding"};
   String Plantation_Labour[]={"No of labour required per acre","Cost of equipment per season","Cost of Labour in a day"};
   String Plant_Care[]={"Fungicide","Plantcare Water required in manure & fertilizer application","Transportation Cost","Periodic Irrigation","Other arrangements","Weeding"};
   String FUngicide[]={"Quantity of seeds re-utilised","Quantity of seeds required per acre ","cost buying seed season","Labour for application"};
   String plantcareQuantity_of_seeds[]={"Seeds actually used","Seeds wasted"};
   String Plant_Carewaterrequired[]={"Water service on lease","Water from Tube well"};
   String PlantcareWaterservice[]={"No of hours & day water required per acre","Cost of water per hour/acre"};
   String Plant_Caretubewell[]={"Cost of Electricity/hr","No of hours & day to moisten the soil","Cost of equipment"};
   String PlantCareTransportationCost[]={"Motorised","Bullock"};
   String PlantCareMotorised[]={"Cost of Vehicle","No of rounds to farmland and Cost of fuel","Vehicle maintenance"};
   String PlantCareBullock[]={"Distance walked by bull","Cost of maintain bull health and feeding"};
   String PlantCarePeriodic_Irrigation[]={"No of labour required per acre","Cost of equipment per season","Cost of Labour in a day"};
   String PlantCareWeeding[]={"Cost of equipments per season","Labour"};
   String HArvesting[]={"Harvesting","Storage","Losses","Transport cost Home to Farm to storage"};
   String HarvestingType[]={"Self","Lease"};
   String SELF[]={"Manual","Mechanical"};
   String MAnual[]={"Labour and cost per acre"};
   String MEchanical[]={"Cost of equipments","Fuel required","Labour"};
   String LEase[]={"Manual","Mechanical"};
   String Lease_Manual[]={"Cost per acre"};
   String Lease_Mechanical[]={"Cost per acre"};
   Button Submit;

   public Enter_Farm_dataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_enter__farm_data, container, false);

        land_ownership=view.findViewById(R.id.LandOwnership);
        tillage=view.findViewById(R.id.tillage);
        tractor=view.findViewById(R.id.tractor);
        own=view.findViewById(R.id.own);
        on_lease=view.findViewById(R.id.on_lease);
        bullock=view.findViewById(R.id.Bullock);
        watering_soil=view.findViewById(R.id.Watering_Soil);
        water_lease=view.findViewById(R.id.Water_lease);
        water_tubewell=view.findViewById(R.id.Water_Tubewell);
        transportation_cost=view.findViewById(R.id.Transportation_Cost);
        motorised=view.findViewById(R.id.Motorised);
        transportatonbullock=view.findViewById(R.id.transportatonBullock);
        manure=view.findViewById(R.id.manure);
        fertilizermanure=view.findViewById(R.id.mmanure);
        home_Produced=view.findViewById(R.id.Home_produced);
        cow_per_season=view.findViewById(R.id.cow_per_season);
        waterrequired=view.findViewById(R.id.waterrequired);
        weteronlease=view.findViewById(R.id.weteronlease);
        wetertubewell=view.findViewById(R.id.wetertubewell);
        fertilizertransportcost=view.findViewById(R.id.Fertilizertransportcost);
        fertilizermotorised=view.findViewById(R.id.fertilizermotorised);
        fertilizerbullock=view.findViewById(R.id.fertilizerbullock);
        manurelabour=view.findViewById(R.id.labour);
        manurefertilizer=view.findViewById(R.id.Fertilizer);
        plantation=view.findViewById(R.id.Plantation);
        seeds=view.findViewById(R.id.Seeds);
        quantityofseeds=view.findViewById(R.id.Quantityofseeds);
        plantation_water_required=view.findViewById(R.id.Plantation_Water_required);
        plantationwaterservices=view.findViewById(R.id.PlantationWaterservices);
        plantation_tubewell=view.findViewById(R.id.Plantation_Tubewell);
        plantation_transportationcost=view.findViewById(R.id.Plantation_TransportationCost);
        plantation_motorised=view.findViewById(R.id.Plantation_Motorised);
        plantation_bullock=view.findViewById(R.id.Plantation_Bullock);
        plantation_labour=view.findViewById(R.id.Plantation_labour);
        plant_care=view.findViewById(R.id.Plant_Care);
        fungicide=view.findViewById(R.id.Fungicide);
        plantcarequantity_of_seeds=view.findViewById(R.id.plantcareQuantity_of_seeds);
        plant_carewaterrequired=view.findViewById(R.id.Plant_Carewaterrequired);
        plantcarewaterservice=view.findViewById(R.id.PlantcareWaterservice);
        plantcaretubewell=view.findViewById(R.id.Plant_Caretubewell);
        plantCaretransportationcost=view.findViewById(R.id.PlantCareTransportationCost);
        plantCaremotorised=view.findViewById(R.id.PlantCareMotorised);
        plantCarebullock=view.findViewById(R.id.PlantCareBullock);
        plantCarePeriodic_irrigation=view.findViewById(R.id.PlantCarePeriodic_Irrigation);
        plantCareweeding=view.findViewById(R.id.PlantCareWeeding);
        harvesting=view.findViewById(R.id.harvesting);
        harvestingtype=view.findViewById(R.id.harvestingtype);
        self=view.findViewById(R.id.Self);
        manual=view.findViewById(R.id.Manual);
        mechanical=view.findViewById(R.id.Mechanical);
        lease=view.findViewById(R.id.Lease);
        lease_manual=view.findViewById(R.id.Lease_manual);
        lease_mechanical=view.findViewById(R.id.Lease_Mechanical);
        Submit=view.findViewById(R.id.Submit);

        ArrayAdapter<String>myland=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Land_Ownership);
        land_ownership.setAdapter(myland);
        ArrayAdapter<String>mytillage=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,TIllage);
        tillage.setAdapter(mytillage);
        ArrayAdapter<String>mytractor=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,TRactor);
        tractor.setAdapter(mytractor);
        ArrayAdapter<String>myown=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,OWN);
        own.setAdapter(myown);
        ArrayAdapter<String>myonlease=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,On_Lease);
        on_lease.setAdapter(myonlease);
        ArrayAdapter<String>mybullock=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,BUllock);
        bullock.setAdapter(mybullock);
        ArrayAdapter<String>mysoil=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Watering_Soil);
        watering_soil.setAdapter(mysoil);
        ArrayAdapter<String>mywaterlease=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Water_Lease);
        water_lease.setAdapter(mywaterlease);
        ArrayAdapter<String>mywatertubewell=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Water_Tubewell);
        water_tubewell.setAdapter(mywatertubewell);
        ArrayAdapter<String>mytransport=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Transportation_Cost);
        transportation_cost.setAdapter(mytransport);
        ArrayAdapter<String>myMotorised=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,MOtorised);
        motorised.setAdapter(myMotorised);
        ArrayAdapter<String>mytransbullock=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,transportatonBullock);
        transportatonbullock.setAdapter(mytransbullock);
        ArrayAdapter<String>mymanure=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,MAnure);
        manure.setAdapter(mymanure);
        ArrayAdapter<String>myfertilizer=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,FertilizerManure);
        fertilizermanure.setAdapter(myfertilizer);
        ArrayAdapter<String>myhome=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Home_Produced);
        home_Produced.setAdapter(myhome);
        ArrayAdapter<String>mycow=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Cow_Per_Season);
        cow_per_season.setAdapter(mycow);
        ArrayAdapter<String>myrequired=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,WaterRequired);
        waterrequired.setAdapter(myrequired);
        ArrayAdapter<String>mywateronlease=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,WeterOnlease);
        weteronlease.setAdapter(mywateronlease);
        ArrayAdapter<String>mywaterontubewell=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,WeterTubewell);
        wetertubewell.setAdapter(mywaterontubewell);
        ArrayAdapter<String>myfertilizertranscost=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,FertilizerTransportCost);
        fertilizertransportcost.setAdapter(myfertilizertranscost);
        ArrayAdapter<String>myfertilizertmotorised=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,FertilizerMotorised);
        fertilizermotorised.setAdapter(myfertilizertmotorised);
        ArrayAdapter<String>myfertilizertbullock=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,FertilizerBullock);
        fertilizerbullock.setAdapter(myfertilizertbullock);
        ArrayAdapter<String>mylabour=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,ManureLabour);
        manurelabour.setAdapter(mylabour);
        ArrayAdapter<String>mymanurefertilizer=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,ManureFertilizer);
        manurefertilizer.setAdapter(mymanurefertilizer);
        ArrayAdapter<String>myplantation=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PLantation);
        plantation.setAdapter(myplantation);
        ArrayAdapter<String>myseads=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,SEeds);
        seeds.setAdapter(myseads);
        ArrayAdapter<String>myquantity=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,QuantityOfseeds);
        quantityofseeds.setAdapter(myquantity);
        ArrayAdapter<String>myPlantationwater=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plantation_Water_Required);
        plantation_water_required.setAdapter(myPlantationwater);
        ArrayAdapter<String>myPlantationwaterservices=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantationWaterservices);
        plantationwaterservices.setAdapter(myPlantationwaterservices);
        ArrayAdapter<String>myplantationtubewell=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plantation_Tubewell);
        plantation_tubewell.setAdapter(myplantationtubewell);
        ArrayAdapter<String>myplantationtransportcost=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plantation_TransportationCost);
        plantation_transportationcost.setAdapter(myplantationtransportcost);
        ArrayAdapter<String>myplantationmotorised=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plantation_Motorised);
        plantation_motorised.setAdapter(myplantationmotorised);
        ArrayAdapter<String>myplantationBullock=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plantation_Bullock);
        plantation_bullock.setAdapter(myplantationBullock);
        ArrayAdapter<String>myplantationlabour=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plantation_Labour);
        plantation_labour.setAdapter(myplantationlabour);
        ArrayAdapter<String>myplantcare=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plant_Care);
        plant_care.setAdapter(myplantcare);
        ArrayAdapter<String>myfungicide=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,FUngicide);
        fungicide.setAdapter(myfungicide);
        ArrayAdapter<String>myquantityofseed=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,plantcareQuantity_of_seeds);
        plantcarequantity_of_seeds.setAdapter(myquantityofseed);
        ArrayAdapter<String>myplantcarewaterrequired=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plant_Carewaterrequired);
        plant_carewaterrequired.setAdapter(myplantcarewaterrequired);
        ArrayAdapter<String>myplantcarewaterservices=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantcareWaterservice);
        plantcarewaterservice.setAdapter(myplantcarewaterservices);
        ArrayAdapter<String>myplantcaretubewell=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Plant_Caretubewell);
        plantcaretubewell.setAdapter(myplantcaretubewell);
        ArrayAdapter<String>myplantcaretransport=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantCareTransportationCost);
        plantCaretransportationcost.setAdapter(myplantcaretransport);
        ArrayAdapter<String>myplantcaremotorised=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantCareMotorised);
        plantCaremotorised.setAdapter(myplantcaremotorised);
        ArrayAdapter<String>myplantcarebullock=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantCareBullock);
        plantCarebullock.setAdapter(myplantcarebullock);
        ArrayAdapter<String>myplantcareperioud=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantCarePeriodic_Irrigation);
        plantCarePeriodic_irrigation.setAdapter(myplantcareperioud);
        ArrayAdapter<String>myplantweeding=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,PlantCareWeeding);
        plantCareweeding.setAdapter(myplantweeding);
        ArrayAdapter<String>myharvesting=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,HArvesting);
        harvesting.setAdapter(myharvesting);
        ArrayAdapter<String>myharvestingtype=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,HarvestingType);
        harvestingtype.setAdapter(myharvestingtype);
        ArrayAdapter<String>myself=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,SELF);
        self.setAdapter(myself);
        ArrayAdapter<String>mymanual=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,MAnual);
        manual.setAdapter(mymanual);
        ArrayAdapter<String>mymechnical=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,MEchanical);
        mechanical.setAdapter(mymechnical);
        ArrayAdapter<String>mylease=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,LEase);
        lease.setAdapter(mylease);
        ArrayAdapter<String>myleasemanual=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Lease_Manual);
        lease_manual.setAdapter(myleasemanual);
        ArrayAdapter<String>myleasemechanical=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,Lease_Mechanical);
        lease_mechanical.setAdapter(myleasemechanical);








        land_ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Land_ownership=land_ownership.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tillage=tillage.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tractor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Tractor=tractor.getSelectedItem().toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
        own.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Own=own.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
        on_lease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          On_lease=on_lease.getSelectedItem().toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
  });
        bullock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          Bullock=bullock.getSelectedItem().toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
  });
        watering_soil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Watering_soil=watering_soil.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        water_lease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Water_lease=water_lease.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        water_tubewell.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Water_tubewell=water_tubewell.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        transportation_cost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Transportation_cost=transportation_cost.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        motorised.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Motorised=motorised.getSelectedItem().toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
        transportatonbullock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Transportatonbullock=transportatonbullock.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        manure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Manure=manure.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fertilizermanure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fertilizerManure=fertilizermanure.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        home_Produced.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Home_produced=home_Produced.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cow_per_season.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cow_per_season=cow_per_season.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        waterrequired.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Waterrequired=waterrequired.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        weteronlease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Weteronlease=weteronlease.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        wetertubewell.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weterTubewell=wetertubewell.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fertilizertransportcost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fertilizertransportcost=fertilizertransportcost.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fertilizermotorised.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fertilizermotorised=fertilizermotorised.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fertilizerbullock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fertilizerbullock=fertilizerbullock.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        manurelabour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Manurelabour=manurelabour.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        manurefertilizer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Manurefertilizer=manurefertilizer.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantation=plantation.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        seeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Seeds=seeds.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        quantityofseeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Quantityofseeds=quantityofseeds.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation_water_required.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantation_Water_required=plantation_water_required.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantationwaterservices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantationwaterservices=plantationwaterservices.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation_tubewell.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantation_tubewell=plantation_tubewell.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation_transportationcost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantation_transportationCost=plantation_transportationcost.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation_motorised.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantation_motorised=plantation_motorised.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation_bullock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             Plantation_bullock=plantation_bullock.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantation_labour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantation_labour=plantation_labour.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plant_care.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plant_care=plant_care.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fungicide.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fungicide=fungicide.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantcarequantity_of_seeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantcarequantity_of_seeds=plantcarequantity_of_seeds.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plant_carewaterrequired.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plant_carewaterrequired=plant_carewaterrequired.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantcarewaterservice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plantcareWaterservice=plantcarewaterservice.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantcaretubewell.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plant_caretubewell=plantcaretubewell.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantCaretransportationcost.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantcaretransportationcost=plantCaretransportationcost.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantCaremotorised.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantcaremotorised=plantCaremotorised.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantCarebullock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PlantCarebullock=plantCarebullock.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantCarePeriodic_irrigation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PlantcarePeriodic_irrigation=plantCarePeriodic_irrigation.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        plantCareweeding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Plantcareweeding=plantCareweeding.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        harvesting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Harvesting=harvesting.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        harvestingtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Harvestingtype=harvestingtype.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        self.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Self=self.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        manual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Manual=manual.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mechanical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Mechanical=mechanical.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Lease=lease.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lease_manual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 Lease_manual=lease_manual.getSelectedItem().toString();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });
        lease_mechanical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Lease_mechanical=lease_mechanical.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog=new ProgressDialog(getContext());
                               dialog.setMessage("Please wait...");
                               dialog.setCanceledOnTouchOutside(false);
                               dialog.show();

                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference reference=db.getReference().child("MyFarm").push();

                HashMap<String,Object> map=new HashMap<>();
                map.put("Land_ownership",Land_ownership);
                map.put("Tillage",Tillage);
                map.put("Tractor",Tractor);
                map.put("Own",Own);
                map.put("On_lease",On_lease);
                map.put("Bullock",Bullock);
                map.put("Watering_soil",Watering_soil);
                map.put("Water_lease",Water_lease);
                map.put("Water_tubewell",Water_tubewell);
                map.put("Transportation_cost",Transportation_cost);
                map.put("Motorised",Motorised);
                map.put("Transportation_bullock",Transportatonbullock);
                map.put("Manure",Manure);
                map.put("fertilizer_Manure",fertilizerManure);
                map.put("Home_produced",Home_produced);
                map.put("Cow_per_season",Cow_per_season);
                map.put("Water_required",Waterrequired);
                map.put("Water_on_lease",Weteronlease);
                map.put("Water_Tubewell",weterTubewell);
                map.put("Fertilizer_transport_cost",Fertilizertransportcost);
                map.put("Fertilizer_motorised",Fertilizermotorised);
                map.put("Fertilizer_bullock",Fertilizerbullock);
                map.put("Manure_labour",Manurelabour);
                map.put("Manure_fertilizer",Manurefertilizer);
                map.put("Plantation",Plantation);
                map.put("Seeds",Seeds);
                map.put("Quantity_of_seeds",Quantityofseeds);
                map.put("Plantation_Water_required",Plantation_Water_required);
                map.put("Plantation_water_services",Plantationwaterservices);
                map.put("Plantation_tubewell",Plantation_tubewell);
                map.put("Plantation_transportation_Cost",Plantation_transportationCost);
                map.put("Plantation_motorised",Plantation_motorised);
                map.put("Plantation_bullock",Plantation_bullock);
                map.put("Plantation_labour",Plantation_labour);
                map.put("Plant_care",Plant_care);
                map.put("Fungicide",Fungicide);
                map.put("Plantcare_quantity_of_seeds",Plantcarequantity_of_seeds);
                map.put("Plantcare_water_required",Plant_carewaterrequired);
                map.put("plantcare_Water_service",plantcareWaterservice);
                map.put("Plantcare_tubewell",Plant_caretubewell);
                map.put("Plantcare_transportation_cost",Plantcaretransportationcost);
                map.put("Plantcare_motorised",Plantcaremotorised);
                map.put("Plantcare_bullock",PlantCarebullock);
                map.put("Plantcare_Periodic_irrigation",PlantcarePeriodic_irrigation);
                map.put("Plantcare_weeding",Plantcareweeding);
                map.put("Harvesting_Storage",Harvesting);
                map.put("Harvesting",Harvestingtype);
                map.put("Self",Self);
                map.put("Manual",Manual);
                map.put("Mechanical",Mechanical);
                map.put("Lease",Lease);
                map.put("Lease_manual",Lease_manual);
                map.put("Lease_mechanical",Lease_mechanical);

                reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            Toasty.success(getContext(),"Data Inserted Successfully",Toasty.LENGTH_LONG,true).show();
                        }
                        else{
                            dialog.hide();
                            Toasty.error(getContext(),"Data is not Inserted",Toasty.LENGTH_LONG,true).show();

                        }
                    }
                });

          }
        });
        return view;
    }
}
