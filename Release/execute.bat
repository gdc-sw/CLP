@echo off
md Ejecutables
md Scripts
md Documentos
md Rollback
copy ..\Desarrollo\SDD\Negocio\template.xlsx FPPR.xlsx
cls
echo Se creo la estructura de release exitosamente!
pause>nul
exit