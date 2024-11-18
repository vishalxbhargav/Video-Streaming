import React, { useState } from 'react'
import upload from '../assets/upload.png'
import { Card, Label, TextInput } from 'flowbite-react';

export default function VideoUpload() {
    const [file,setFile]=useState(null);
    const [progress,setProgress]=useState(0)
    const [uploading,setUploading]=useState(false);
    const [message,setMessage]=useState("");

    const uploadFileHandler=()=>{
        console.log(file)
    }
  return (
    <div className='py-6'>
      <Card>
        <h1 className='text-gray-400'>Upload Videos</h1>
        <form className="space-y-5 gap-2">
          <div className='p-2'>
            <div>
              <Label htmlFor='file-upload' value='Upload file'/>
            </div>
            <TextInput placeholder='Enter title'/>
          </div>
          <div className='flex items-center justify-center space-x-6'>
          <div className="shrink-0">
          <img className="h-16 w-16 object-cover rounded-full" src={upload} alt="Upload icon" />
        </div>
        <label className="block">
          <span className="sr-only">Choose video file</span>
          <input 
            type="file" 
            onChange={(e)=>setFile(e.target.files[0])}
            className="block w-full text-sm text-slate-500
                      file:mr-4 file:py-2 file:px-4
                      file:rounded-full file:border-0
                      file:text-sm file:font-semibold
                      file:bg-violet-50 file:text-violet-700
                      hover:file:bg-violet-100"
          />
        </label>
          </div>
      </form>

      <div className="flex justify-center">
        <button 
            onClick={uploadFileHandler}
          className="mt-4 py-2 px-4 text-white bg-blue-600 rounded-full 
                    hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500">
          Upload
        </button>
      </div>

      </Card>
    </div>
  )
}
