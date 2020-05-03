# grndctl-akka API Documentation [![Build Status](https://travis-ci.org/mdisalvo/grndctl-akka.svg)](https://travis-ci.org/mdisalvo/grndctl-akka)
##### michael.vincent.disalvo@gmail.com

| Verb: Endpoint | Description | Params  | Response Entity | Example | Response Codes |
|:---------------|:------------|:--------|:----------------|:--------|:---------------|
|*__AIREPs__* |-|-|-|-|
| `GET: /airep` | Get AIREPs | hrsBefore(r)::double <br> reportType::ReportType | [AircraftReport] | GET /airep?hrsBefore=2.0&reportType=AIREP | 200::OK <br> 400::BadRequest |
|*__Airlines__* | | | | |
| `GET: /airline` | Get All Airlines | - | [Airline] | GET /airline | 200::OK |
| `GET: /airline/icao/{icaoCode}` | Get Airline by ICAO code | - | Airline | GET /airline/icao/UAL | 200::OK <br> 404::NotFound |
| `GET: /airline/iata/{iataCode}` | Get Airline by IATA code | - | Airline | GET /airline/iata/UA | 200::OK <br> 404::NotFound |
| `GET: /airline/active` | Get active Airlines | - | [Airline] | GET /airline/active | 200::OK |
|*__Conversions__* | | | | |
| `GET: /conversions/FtoC` | Convert temp F to temp C | tempF(r)::double | ConversionResult | GET /conversions/FtoC?tempF=98.6 | 200::OK <br> 400::BadRequest |
| `GET: /conversions/CtoF` | Convert temp C to tempF | tempC(r)::double | ConversionResult | GET /conversions/CtoF?tempC=0 | 200::OK <br> 400::BadRequest |
| `GET: /conversions/inchesToMillibars` | Convert pressure from inches of mercury to millibars | pressInches(r)::double | ConversionResult | GET /conversions/inchesToMillibars?pressInches=29.92 | 200::OK <br> 400::BadRequest |
| `GET: /conversions/millibarsToInches` | Convert pressure from millibars to inches of mercury | pressMillis(r)::double | ConversionResult | GET /conversions/millibarsToInches?pressMillis=1013.2 | 200::OK <br> 400::BadRequest |
| `GET: /conversions/windcomponent` | Determine windcomponent given windspeed(Kts), wind direction(From) and heading | windspeed(r)::double <br> winddirection(r)::double <br> heading(r)::double | WindComponent | GET /conversions/windcomponent?windspeed=10&winddirection=210&heading=350 |200::OK <br> 400::BadRequest |
|*__METARs__* | | | | |
| `GET: /metar/{icaoCode}` | Get METARs by ICAO code | hrsBefore::double | [METAR] | GET /metar/KIAD?hrsBefore=2.0 | 200::OK <br> 400::BadRequest <br> 404::NotFound |
|*__Navaids__* | | | | |
| `GET: /navaid` | Get ALL Navaids by identifier | - | [String, [Navaid]] | GET /navaid | 200::OK |
| `GET: /navaid/ident/{ident}` | Get Navaids by identifier | - | [Navaid] | GET /navaid/ident/AL | 200::OK <br> 404::NotFound |
| `GET: /navaid/ident/{station}` | Get Navaids by station | - | [Navaid] | GET /navaid/ident/KDEN | 200::OK <br> 404::NotFound |
|*__NOTAMs__* | | | | |
| `GET: /notam` | Get NOTAMs by ICAO code, report and format types (returns a stringified JSON array) | code(r)::string <br> reportType::ReportType <br> formatType::FormatType | String | GET /notam?code=KDEN&reportType=RAW&formatType=DOMESTIC | 200::OK <br> 400::BadRequest <br> 404::NotFound |
|*__Stations__* | | | | |
| `GET: /station/adds/{icaoCode}` | Get station information from ADDS by ICAO code | - | [Station] | GET /station/adds/KDEN | 200::OK <br> 404::NotFound |
|*__TAFs__* | | | | |
| `GET: /taf/{icaoCode}` | Get TAFs by ICAO code | hrsBefore::double <br> timeType::TimeType | [TAF] | GET /taf/KDEN?hrsBefore=2.0&timeType=VALID | 200::OK <br> 400::BadRequest <br> 404::NotFound |



