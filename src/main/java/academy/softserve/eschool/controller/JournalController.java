package academy.softserve.eschool.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.softserve.eschool.dto.JournalDTO;
import academy.softserve.eschool.dto.JournalMarkDTO;
import academy.softserve.eschool.service.JournalServiceImpl;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "Journal's operations", description = "Get journals")
@RequestMapping("/journals")
@RequiredArgsConstructor
public class JournalController {

    @NonNull
    JournalServiceImpl journalServiceImpl;

    @ApiOperation(value = "Get list of all journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("")
    public GeneralResponseWrapper<List<JournalDTO>> getJournals(){
        return new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getJournals());
    }

    @ApiOperation(value = "Get list of all teacher's journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teachers/{idTeacher}")
    public GeneralResponseWrapper<List<JournalDTO>> getJournalsTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){
        return new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getJournalsByTeacher(idTeacher));
    }

    @ApiOperation(value = "Get list of active teacher's journals")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teachers/{idTeacher}/active")
    public GeneralResponseWrapper<List<JournalDTO>> getActiveJournalsTeacher(
            @ApiParam(value = "id of teacher", required = true) @PathVariable int idTeacher){
        return new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getActiveJournalsByTeacher(idTeacher));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 400, message = "Bad request"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    @ApiOperation(value = "Get journal by subjects and classes")
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/subjects/{idSubject}/classes/{idClass}")
    public GeneralResponseWrapper<List<JournalMarkDTO>> getJournalTable(
            @ApiParam(value = "id of subject", required = true) @PathVariable int idSubject,
            @ApiParam(value = "id of class", required = true) @PathVariable int idClass
            ){
        return new GeneralResponseWrapper<>(new Status(200, "OK"), journalServiceImpl.getJournal(idSubject,idClass));
    }
}
