package com.hotels.test.controllers;

import com.hotels.test.DTO.HotelCreateRequest;
import com.hotels.test.DTO.HotelSummaryDTO;
import com.hotels.test.DTO.HotelSummaryResponse;
import com.hotels.test.entities.Hotel;
import com.hotels.test.services.HotelService;
import com.hotels.test.services.AddressService;
import com.hotels.test.services.ArrivalTimeService;
import com.hotels.test.services.ContactsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/property-view")
@Tag(name = "Hotel Management", description = "API для управления отелями")
public class controller {
    private final HotelService hotelService;
    private final AddressService addressService;
    private final ArrivalTimeService arrivalTimeService;
    private final ContactsService contactsService;

    @Autowired
    public controller(HotelService hotelService,
                      AddressService addressService,
                      ArrivalTimeService arrivalTimeService,
                      ContactsService contactsService) {
        this.hotelService = hotelService;
        this.addressService = addressService;
        this.arrivalTimeService = arrivalTimeService;
        this.contactsService = contactsService;
    }

    @GetMapping(value="hotels")
    @Operation(summary = "Получить все отели", description = "Возвращает список всех отелей в системе")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешно получен список отелей",
            content = @Content(schema = @Schema(implementation = HotelSummaryDTO.class))),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<List<HotelSummaryDTO>> hotels(){
        try {
            return ResponseEntity.ok(hotelService.getAllHotels());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/hotels/{id}")
    @Operation(summary = "Получить отель по ID", description = "Возвращает детальную информацию об отеле по его ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Отель найден",
            content = @Content(schema = @Schema(implementation = Hotel.class))),
        @ApiResponse(responseCode = "404", description = "Отель не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<Hotel> getInfo(@Parameter(description = "ID отеля") @PathVariable("id") int id){
        try {
            Optional<Hotel> hotel = hotelService.getHotelById(id);
            if (hotel.isPresent()) {
                return ResponseEntity.ok(hotel.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/search")
    @Operation(summary = "Поиск отелей", description = "Поиск отелей по различным критериям")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Поиск выполнен успешно",
            content = @Content(schema = @Schema(implementation = HotelSummaryDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные параметры поиска")
    })
    public ResponseEntity<List<HotelSummaryDTO>> search(
            @Parameter(description = "Название отеля") @RequestParam(required = false) String name,
            @Parameter(description = "Бренд отеля") @RequestParam(required = false) String brand,
            @Parameter(description = "Город") @RequestParam(required = false) String city,
            @Parameter(description = "Страна") @RequestParam(required = false) String country,
            @Parameter(description = "Список удобств") @RequestParam(required = false) List<String> amenities
    ){
        try {
            List<HotelSummaryDTO> hotels = hotelService.searchByArgument(name, brand, city, country, amenities);
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Получить гистограмму", description = "Получить количество отелей, сгруппированных по параметру")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Гистограмма построена успешно"),
        @ApiResponse(responseCode = "400", description = "Неизвестный параметр")
    })
    public ResponseEntity<Map<String, Integer>> getHistogram(
            @Parameter(description = "Параметр для группировки (brand, city, country, amenities)") 
            @PathVariable String param) {
        try {
            Map<String, Integer> histogram = hotelService.histogramByParam(param);
            return ResponseEntity.ok(histogram);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/hotels")
    @Operation(summary = "Создать новый отель", description = "Создает новый отель в системе")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Отель создан успешно",
            content = @Content(schema = @Schema(implementation = HotelSummaryDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные отеля")
    })
    public ResponseEntity<HotelSummaryDTO> createHotel(
            @Parameter(description = "Данные для создания отеля") 
            @Valid @RequestBody HotelCreateRequest request) {
        try {
            HotelSummaryDTO createdHotel = hotelService.createHotel(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Добавить удобства к отелю", description = "Добавляет список удобств к существующему отелю")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Удобства добавлены успешно"),
        @ApiResponse(responseCode = "400", description = "Отель не найден или некорректные данные")
    })
    public ResponseEntity<Void> addAmenitiesToHotel(
            @Parameter(description = "ID отеля") @PathVariable int id, 
            @Parameter(description = "Список удобств для добавления") @RequestBody List<String> amenities) {
        try {
            hotelService.addAmenitiesToHotel(id, amenities);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/hello")
    @Operation(summary = "Тестовый endpoint", description = "Простой тестовый endpoint для проверки работы API")
    public String getString(){
        return "There is hello right now ()()()()()()()()()";
    }
}
