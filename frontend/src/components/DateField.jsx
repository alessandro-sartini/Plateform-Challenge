import ReactDatePicker from "react-datepicker";



export default function DateField({ value, onChange}) {
    return (
        <div className="flex items-center flex-1 border border-gray-200 bg-white px-4 py-3 md:py-2 md:rounded-none md:border-l-0 cursor-pointer ">
            <label htmlFor="date" className="sr-only">Data</label>
            <span className="text-gray-700 mr-2">
                <svg className="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24">
                    <rect x="3" y="4" width="18" height="18" rx="4" />
                    <path d="M16 2v4M8 2v4" />
                    <path d="M3 10h18" />
                </svg>
            </span>
            <ReactDatePicker
                id="date"
                className="bg-transparent focus:outline-none focus:ring-0 focus:ring-orange-400 w-full text-gray-800 text-base placeholder-gray-400 cursor-pointer "
                selected={value}
                onChange={onChange}
                dateFormat="dd/MM/yyyy"
                placeholderText="Scegli la data"
                minDate={new Date()}
                inputReadOnly
                showPopperArrow={false}
            />
        </div>
    )
}
